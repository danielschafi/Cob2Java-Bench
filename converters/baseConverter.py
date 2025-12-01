from abc import ABC, abstractmethod


class BaseCob2JavaConverter(ABC):
    """
    Base class for COBOL to Java converters. All converters should inherit from this class
    and implement the convert method.
    """

    @abstractmethod
    def convert(self, cobol_code: str) -> str:
        """
        Convert COBOL code to Java code.

        Args:
            cobol_code (str): The COBOL source code as a string.

        Returns:
            str: The converted Java source code as a string.
        """
        pass
