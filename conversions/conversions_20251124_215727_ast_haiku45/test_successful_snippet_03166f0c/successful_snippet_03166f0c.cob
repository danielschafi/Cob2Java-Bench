      *************************************
      * hammer.cob
      *
      * When your only tool is a hammer...
      *************************************
      
       identification division.
       program-id. hammer.
       
       environment division.
       
       input-output section.
       file-control.           
           select html-file
           assign to "hammer.html"
           organization is line sequential.
           

       data division.
       
       file section.
       fd html-file.
       01 html-line				pic x(255).
       
       working-storage section.
       01 user-message				pic x(255) value space.
       78 default-message			value "HELLO WORLD".
       
       
       procedure division.
       
      * Get user input
       display "Enter a a message: ".
       accept user-message.

       if user-message equals space then
           move default-message		to user-message
       end-if.
       
      * Open html file
       open output html-file.
       
      * Write some html
       write html-line 			from "<html><head>".
       
      * Gotta have some bootstrap
       perform write-bootstrap-link.
       
      * Load Google web fonts
       perform write-web-fonts.
       
      * Write style
       perform write-style.
      
       write html-line 			from "</head>".
       
       initialize html-line.
       string 	"<body>"
       	"<div class=" quote "container text-center" quote ">"
       	"<h1 class=" quote "when-your-only" quote ">"
       	"When your only tool is COBOL, everything looks like a "
       	"mainframe"
       	"</h1>"			into html-line.
       write html-line.
      
       initialize html-line.
       string	"<h1 class=" quote "display-1" quote "> " 
       	"<span class=" quote "hello-world" quote ">"	
       					into html-line.
       write html-line.
       
       write html-line 			from user-message.
       
       initialize html-line.
       string	"</span>"
        	"</h1></div></body></html>"	into html-line.
       write html-line.  	

      * Close html file
       close html-file.

       exit program.
       stop run.
       
       
       write-style.
      * Add inline style 
           initialize html-line.
           string "<style>"
        	   ".hello-world {"
  		      "font-family: 'VT323', sans-serif;"
  		      "padding: 5rem 0;"
		   "}"
		   ".when-your-only {"
		      "font-family: 'Cousine', sans-serif;"
		      "margin: 4rem 0;"
		   "}"
		   "</style>"	 
           					into html-line.	
           write html-line.

       write-bootstrap-link.
      * Gotta have some bootstrap
           initialize html-line.
           string "<link href=" quote 
       	   "https://cdn.jsdelivr.net/npm/"	
                  "bootstrap@5.0.2/dist/css/bootstrap.min.css" quote 
                  "rel=" quote "stylesheet" quote ">" 
                  				into html-line.
           write html-line.
           
       write-web-fonts.
      * Load Google web fonts 
           initialize html-line.
      	   string "<link href=" quote
      	          "https://fonts.googleapis.com/css?family="
		  "VT323|Cousine" quote 
      	          "rel=" quote "stylesheet" quote 
      	          "type=" quote "text/css" quote ">"	
      	          				into html-line.
      	   write html-line.
          
       
