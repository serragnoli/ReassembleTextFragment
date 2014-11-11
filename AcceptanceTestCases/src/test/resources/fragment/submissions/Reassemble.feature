Feature: Reassembling
  In order to save my arse
  As a person in deep trouble
  I want to be able to reassemble text fragments into a soft copy
 
	
  Scenario: Reassemble fragments of short text
    Given the contents of the file "./target/test-classes/short_file.txt" contain fragments of text
	When it is reassembled
	Then it should print
	"""
	O draconian devil! Oh lame saint!
	"""
  
  Scenario: Reassemble fragments of long text
    Given the contents of the file "./target/test-classes/long_file.txt" contain fragments of text
    When it is reassembled
    Then it should print
    """
    Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit, sed quia non numquam eius modi tempora incidunt ut labore et dolore magnam aliquam quaerat voluptatem
    """
    
  Scenario: Reassemble fragments that contain two paragraphs
    Given the contents of the file "./target/test-classes/two_paragraphs_file.txt" contain fragments of text of two paragraphs
    When is is reassembled
    Then it should print
    """
    First paragraph. Second paragraph
    """