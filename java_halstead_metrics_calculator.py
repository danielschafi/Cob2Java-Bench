import javalang
import math
from collections import Counter
from lizard import analyze_file, FileAnalyzer

"""
Code is by Claude and ChatGPT usw with caution
But should be good enough for comparision of different converters
"""


class JavaHalsteadCalculator:
    """
    Calculate Halstead complexity metrics for Java source code using proper AST parsing.
    """

    def __init__(self):
        self.operators = []
        self.operands = []

    def calculate_metrics(self, java_code, file_path=None):
        """
        Calculate Halstead metrics for the given Java code.

        Args:
            java_code (str): Java source code as a string
            file_path (str, optional): Path to the Java file (for Lizard analysis)

        Returns:
            dict: Dictionary containing Halstead metrics and maintenance index
        """
        self.operators = []
        self.operands = []

        try:
            # Parse the Java code into an AST
            tree = javalang.parse.parse(java_code)

            # Traverse the AST and collect operators and operands
            self._traverse(tree)

            # Calculate Halstead metrics
            n1 = len(set(self.operators))  # Distinct operators
            n2 = len(set(self.operands))  # Distinct operands
            N1 = len(self.operators)  # Total operators
            N2 = len(self.operands)  # Total operands

            # Calculate derived metrics
            vocabulary = n1 + n2
            length = N1 + N2

            if vocabulary > 0:
                volume = length * math.log2(vocabulary)
            else:
                volume = 0

            if n2 > 0:
                difficulty = (n1 / 2) * (N2 / n2)
            else:
                difficulty = 0

            effort = difficulty * volume

            # Get cyclomatic complexity from Lizard
            cyclomatic_complexity = self._get_cyclomatic_complexity(
                java_code, file_path
            )

            # Calculate Lines of Code (non-blank, non-comment lines)
            loc = self._calculate_loc(java_code)

            # Calculate Maintainability Index using Visual Studio formula
            # MI = MAX(0,(171 - 5.2 * ln(Halstead Volume) - 0.23 * (Cyclomatic Complexity) - 16.2 * ln(Lines of Code))*100 / 171)
            if volume > 0 and loc > 0:
                mi_raw = (
                    171
                    - 5.2 * math.log(volume)
                    - 0.23 * cyclomatic_complexity
                    - 16.2 * math.log(loc)
                )
                mi = max(0, (mi_raw * 100) / 171)
            else:
                mi = 100

            # Determine maintainability rating
            if mi < 10:
                rating = "Low maintainability"
            elif mi < 20:
                rating = "Moderate maintainability"
            else:
                rating = "Good maintainability"

            return {
                "n1": n1,  # Distinct operators
                "n2": n2,  # Distinct operands
                "N1": N1,  # Total operators
                "N2": N2,  # Total operands
                "vocabulary": vocabulary,
                "length": length,
                "volume": volume,
                "difficulty": difficulty,
                "effort": effort,
                "cyclomatic_complexity": cyclomatic_complexity,
                "lines_of_code": loc,
                "maintainability_index": mi,
                "maintainability_rating": rating,
                "operators": sorted(Counter(self.operators).items()),
                "operands": sorted(Counter(self.operands).items()),
            }

        except Exception as e:
            print(e)
            return {
                "error": str(e),
                "n1": 0,
                "n2": 0,
                "N1": 0,
                "N2": 0,
                "volume": 0,
                "difficulty": 0,
                "effort": 0,
                "cyclomatic_complexity": 0,
                "lines_of_code": 0,
                "maintainability_index": 0,
                "maintainability_rating": "Unknown",
            }

    def _get_cyclomatic_complexity(self, java_code, file_path=None):
        """
        Calculate cyclomatic complexity using Lizard.

        Args:
            java_code (str): Java source code
            file_path (str, optional): File path for Lizard

        Returns:
            int: Total cyclomatic complexity
        """
        try:
            if file_path:
                # Analyze from file
                analysis = analyze_file(file_path)
                total_complexity = sum(
                    func.cyclomatic_complexity for func in analysis.function_list
                )
            else:
                # Analyze from string
                import tempfile
                import os

                # Create a temporary file
                with tempfile.NamedTemporaryFile(
                    mode="w", suffix=".java", delete=False
                ) as tmp:
                    tmp.write(java_code)
                    tmp_path = tmp.name

                try:
                    analysis = analyze_file(tmp_path)
                    total_complexity = sum(
                        func.cyclomatic_complexity for func in analysis.function_list
                    )
                finally:
                    # Clean up temporary file
                    os.unlink(tmp_path)

            return total_complexity if total_complexity > 0 else 1

        except Exception as e:
            # Fallback to a simple estimate based on control flow keywords
            control_keywords = [
                "if",
                "else",
                "while",
                "for",
                "case",
                "catch",
                "&&",
                "||",
                "?",
            ]
            complexity = 1  # Base complexity
            for keyword in control_keywords:
                complexity += self.operators.count(keyword)
            return complexity

    def _calculate_loc(self, java_code):
        """
        Calculate lines of code (non-blank, non-comment lines).

        Args:
            java_code (str): Java source code

        Returns:
            int: Number of lines of code
        """
        lines = java_code.split("\n")
        loc = 0
        in_multiline_comment = False

        for line in lines:
            stripped = line.strip()

            # Skip empty lines
            if not stripped:
                continue

            # Handle multi-line comments
            if "/*" in stripped:
                in_multiline_comment = True
            if "*/" in stripped:
                in_multiline_comment = False
                continue

            # Skip lines in multi-line comments
            if in_multiline_comment:
                continue

            # Skip single-line comments
            if stripped.startswith("//"):
                continue

            loc += 1

        return max(loc, 1)  # At least 1 line

    def _traverse(self, node):
        """Recursively traverse the AST and collect operators and operands."""

        if node is None:
            return

        node_type = type(node).__name__

        # Helper to safely traverse attribute names that vary between javalang versions
        def _try_traverse(*attr_names):
            for name in attr_names:
                if hasattr(node, name):
                    try:
                        val = getattr(node, name)
                    except Exception:
                        val = None
                    if val is None:
                        continue
                    self._traverse(val)
                    return True
            return False

        # Binary operators (handle different attribute names)
        if (
            node_type in ("BinaryOperation", "BinaryOp")
            or hasattr(node, "operator")
            and hasattr(node, "operandl")
        ):
            op = getattr(node, "operator", None)
            if op:
                self.operators.append(op)
            # try common left/right attribute names
            _try_traverse("operandl", "left", "loperand", "operand")
            _try_traverse("operandr", "right", "roperand")
            return

        # Unary operators (prefix and postfix)
        if (
            node_type in ("PrefixExpression", "PostfixExpression", "UnaryOperation")
            or hasattr(node, "operator")
            and hasattr(node, "expression")
        ):
            op = getattr(node, "operator", None)
            if op:
                self.operators.append(op)
            _try_traverse("expression", "operand")
            return

        # Assignment operators
        if node_type == "Assignment" or node.__class__.__name__ == "Assignment":
            # Determine operator string (fall back to '=' if unknown)
            op = getattr(node, "operator", None) or getattr(node, "type", None) or "="
            # javalang may use enums for type, convert to string
            try:
                op_str = str(op)
            except Exception:
                op_str = "="
            self.operators.append(op_str)
            # traverse left and right side with fallbacks for attribute names
            _try_traverse("expressionl", "lvalue", "lhs", "left")
            _try_traverse("value", "rhs", "right")
            return

        # Ternary operator
        if isinstance(node, javalang.tree.TernaryExpression):
            self.operators.append("?:")
            self._traverse(node.condition)
            self._traverse(node.if_true)
            self._traverse(node.if_false)
            return

        # Method invocations (method name is an operator)
        if isinstance(node, javalang.tree.MethodInvocation):
            # Treat method invocation as a 'call' operator and the method name as an operand
            self.operators.append("call")
            if getattr(node, "member", None):
                self.operands.append(node.member)

            # Process qualifier chains like System.out (qualifier.parts are operands)
            qualifier = getattr(node, "qualifier", None)
            if qualifier:
                if isinstance(qualifier, str):
                    parts = [p for p in qualifier.split(".") if p]
                    # each qualifier part is an operand; there are len(parts) dot transitions before the method
                    for part in parts:
                        self.operands.append(part)
                    for _ in parts:
                        self.operators.append(".")
                else:
                    # For non-string qualifiers (MemberReference or other node), traverse it
                    self._traverse(qualifier)

            # Traverse method arguments
            if getattr(node, "arguments", None):
                for arg in node.arguments:
                    self._traverse(arg)
            return

        # Constructor invocations
        if isinstance(node, javalang.tree.ClassCreator):
            self.operators.append("new")
            if node.constructor_type_arguments:
                for arg in node.constructor_type_arguments:
                    self._traverse(arg)
            if node.arguments:
                for arg in node.arguments:
                    self._traverse(arg)
            return

        # Array access
        if isinstance(node, javalang.tree.ArraySelector):
            self.operators.append("[]")
            self._traverse(node.index)
            return

        # Member access (dot operator) and variable references
        if isinstance(node, javalang.tree.MemberReference):
            qualifier = getattr(node, "qualifier", None)
            # If qualifier is a dotted string like "System.out", split it
            if qualifier and isinstance(qualifier, str):
                parts = [p for p in qualifier.split(".") if p]
                for part in parts:
                    self.operands.append(part)
                    self.operators.append(".")
            elif qualifier:
                # traverse qualifier node to collect operands
                self._traverse(qualifier)

            # member is the final operand
            if getattr(node, "member", None):
                self.operands.append(node.member)
            return

        # Cast operator
        if isinstance(node, javalang.tree.Cast):
            self.operators.append("(cast)")
            self._traverse(node.expression)
            return

        # instanceof operator
        if (
            node_type == "BinaryOperation"
            and hasattr(node, "operator")
            and node.operator == "instanceof"
        ):
            self.operators.append("instanceof")
            self._traverse(node.operandl)
            self._traverse(node.operandr)
            return

        # Literals (operands)
        if isinstance(node, javalang.tree.Literal):
            self.operands.append(f"'{node.value}'")
            return

        # Method declarations: record method name as an operand and traverse params/body
        if isinstance(node, javalang.tree.MethodDeclaration):
            if getattr(node, "name", None):
                self.operands.append(node.name)
            # parameters
            if getattr(node, "parameters", None):
                for p in node.parameters:
                    self._traverse(p)
            # body (may be list of statements)
            if getattr(node, "body", None):
                self._traverse(node.body)
            return

        # Variable declarators (operands)
        if isinstance(node, javalang.tree.VariableDeclarator):
            # variable name is an operand
            if getattr(node, "name", None):
                self.operands.append(node.name)
            # if there's an initializer, count an assignment operator and traverse it
            if getattr(node, "initializer", None):
                self.operators.append("=")
                self._traverse(node.initializer)
            return

        # Formal parameters (operands)
        if isinstance(node, javalang.tree.FormalParameter):
            self.operands.append(node.name)
            return

        # Control flow statements (operators)
        if isinstance(node, javalang.tree.IfStatement):
            self.operators.append("if")
            self._traverse(node.condition)
            self._traverse(node.then_statement)
            if node.else_statement:
                self.operators.append("else")
                self._traverse(node.else_statement)
            return

        if isinstance(node, javalang.tree.WhileStatement):
            self.operators.append("while")
            self._traverse(node.condition)
            self._traverse(node.body)
            return

        if isinstance(node, javalang.tree.DoStatement):
            self.operators.append("do")
            self._traverse(node.condition)
            self._traverse(node.body)
            return

        if isinstance(node, javalang.tree.ForStatement):
            self.operators.append("for")
            if node.control:
                self._traverse(node.control)
            self._traverse(node.body)
            return

        if isinstance(node, javalang.tree.SwitchStatement):
            self.operators.append("switch")
            self._traverse(node.expression)
            for case in node.cases:
                self._traverse(case)
            return

        if isinstance(node, javalang.tree.SwitchStatementCase):
            if node.case:
                self.operators.append("case")
                for expr in node.case:
                    self._traverse(expr)
            else:
                self.operators.append("default")
            if node.statements:
                for stmt in node.statements:
                    self._traverse(stmt)
            return

        if isinstance(node, javalang.tree.ReturnStatement):
            self.operators.append("return")
            if node.expression:
                self._traverse(node.expression)
            return

        if isinstance(node, javalang.tree.ThrowStatement):
            self.operators.append("throw")
            self._traverse(node.expression)
            return

        if isinstance(node, javalang.tree.TryStatement):
            self.operators.append("try")
            if node.block:
                for stmt in node.block:
                    self._traverse(stmt)
            if node.catches:
                for catch in node.catches:
                    self.operators.append("catch")
                    self._traverse(catch)
            if node.finally_block:
                self.operators.append("finally")
                for stmt in node.finally_block:
                    self._traverse(stmt)
            return

        # Generic traversal for other node types
        if isinstance(node, list):
            for item in node:
                self._traverse(item)
        elif hasattr(node, "__dict__"):
            for attr_name, attr_value in node.__dict__.items():
                if attr_name.startswith("_"):
                    continue
                self._traverse(attr_value)
        elif hasattr(node, "__iter__") and not isinstance(node, str):
            for item in node:
                self._traverse(item)


# Example usage
if __name__ == "__main__":
    calculator = JavaHalsteadCalculator()

    # Example Java code
    java_code = """
    public class Example {
        public int calculateSum(int a, int b) {
            int result = a + b;
            if (result > 100) {
                return result * 2;
            } else {
                return result;
            }
        }
        
        public void printMessage(String message) {
            System.out.println("Message: " + message);
        }
    }
    """

    metrics = calculator.calculate_metrics(java_code)

    print("=== Halstead Metrics ===")
    print(f"Distinct Operators (n1): {metrics['n1']}")
    print(f"Distinct Operands (n2): {metrics['n2']}")
    print(f"Total Operators (N1): {metrics['N1']}")
    print(f"Total Operands (N2): {metrics['N2']}")
    print(f"Vocabulary (n1 + n2): {metrics['vocabulary']}")
    print(f"Length (N1 + N2): {metrics['length']}")
    print(f"Volume: {metrics['volume']:.2f}")
    print(f"Difficulty: {metrics['difficulty']:.2f}")
    print(f"Effort: {metrics['effort']:.2f}")

    print("\n=== Code Complexity ===")
    print(f"Cyclomatic Complexity: {metrics['cyclomatic_complexity']}")
    print(f"Lines of Code: {metrics['lines_of_code']}")

    print("\n=== Maintainability Index (Visual Studio Formula) ===")
    print(f"MI Score: {metrics['maintainability_index']:.2f}")
    print(f"Rating: {metrics['maintainability_rating']}")
    print(f"\nOperators: {metrics['operators'][:10]}")  # Show first 10
    print(f"Operands: {metrics['operands'][:10]}")  # Show first 10
