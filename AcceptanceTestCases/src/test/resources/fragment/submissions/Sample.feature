Feature: Reassembling
  In order to save my arse
  As a person in deep trouble
  I want to be able to reassemble text fragments into a soft copy
 
	
  Scenario: Reassemble fragments of short text
    Given the contents of the file "./target/test-classes/short_file.txt" contain fragments of text
	When it is reassembled
	Then the following line should be printed
	"""
	O draconian devil! Oh lame saint!
	"""