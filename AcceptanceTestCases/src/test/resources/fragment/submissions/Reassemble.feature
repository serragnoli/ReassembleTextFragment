Feature: Reassembling
  In order to save my arse
  As a person in deep trouble
  I want to be able to reassemble text fragments into a soft copy
 
	
  Scenario: Reassemble fragments of short text in sequence
    Given the fragments "O draconia;conian devil! Oh la;h lame sa;saint!"
	When it is reassembled
	Then it should print
	"""
	O draconian devil! Oh lame saint!
	"""
  
   Scenario: Reassemble fragments of short text back to front
    Given the fragments "saint!;h lame sa;conian devil! Oh la;O draconia"
	When it is reassembled
	Then it should print
	"""
	O draconian devil! Oh lame saint!
	"""
   
   Scenario: Reassemble fragments of short text mixed up
    Given the fragments "saint!;conian devil! Oh la;h lame sa;O draconia"
	When it is reassembled
	Then it should print
	"""
	O draconian devil! Oh lame saint!
	"""
	
  Scenario: Reassemble fragments of long text
    Given the fragments "m quaerat voluptatem.;pora incidunt ut labore et d;, consectetur, adipisci velit;olore magnam aliqua;idunt ut labore et dolore magn;uptatem.;i dolorem ipsum qu;iquam quaerat vol;psum quia dolor sit amet, consectetur, a;ia dolor sit amet, conse;squam est, qui do;Neque porro quisquam est, qu;aerat voluptatem.;m eius modi tem;Neque porro qui;, sed quia non numquam ei;lorem ipsum quia dolor sit amet;ctetur, adipisci velit, sed quia non numq;unt ut labore et dolore magnam aliquam qu;dipisci velit, sed quia non numqua;us modi tempora incid;Neque porro quisquam est, qui dolorem i;uam eius modi tem;pora inc;am al"
    When it is reassembled
    Then it should print
    """
    Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit, sed quia non numquam eius modi tempora incidunt ut labore et dolore magnam aliquam quaerat voluptatem
    """
    
  Scenario: Reassemble fragments that contain two paragraphs
    Given the contents of the file "./target/test-classes/two_paragraphs_file.txt" contain fragments of text of two paragraphs
    When it is reassembled
    Then it should print
    """
    First paragraph. Second paragraph
    """