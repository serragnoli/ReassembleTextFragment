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
    Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit, sed quia non numquam eius modi tempora incidunt ut labore et dolore magnam aliquam quaerat voluptatem.
    """

  Scenario: Reassemble fragments of long text back to front
    Given the fragments "am al;pora inc;uam eius modi tem;us modi tempora incid;dipisci velit, sed quia non numqua;dipisci velit, sed quia non numqua;unt ut labore et dolore magnam aliquam qu;ctetur, adipisci velit, sed quia non numq;lorem ipsum quia dolor sit amet;, sed quia non numquam ei;Neque porro qui;m eius modi tem;aerat voluptatem.;Neque porro quisquam est, qu;squam est, qui do;ia dolor sit amet, conse;psum quia dolor sit amet, consectetur, a;iquam quaerat vol;i dolorem ipsum qu;uptatem.;idunt ut labore et dolore magn;olore magnam aliqua;, consectetur, adipisci velit;pora incidunt ut labore et d;m quaerat voluptatem."
    When it is reassembled
    Then it should print
    """
    Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit, sed quia non numquam eius modi tempora incidunt ut labore et dolore magnam aliquam quaerat voluptatem.
    """
    
  Scenario: Reassemble fragments with text with length of two
    Given the fragments "e.;pe.;m is located in Europe.;in Europe.;m is located in Europe.;The United ;rope.;ted in Europe.;The United Kingdom is located in;The United Kingdom is loc;Kingdom is located in E;ted Kingdom is located in Europe.;The U;The United Kingdom;in Europe.;dom is located in Europe.;The United Kingdom is located in;s located in Europe.;ted Kingdom is located in Europe.;The United Kingdom is ;ted in Europe.; located in Europe.;The United Kingdom is located in Europ;he United Kingdo;is located in Europe.;The Unit;ope.;ngdom is located in Europe.;e United ;The United Kingdom is loca;ted in Europe.;cated in Europe.;The United Kingdom is l; in Europe.;n Europe.;The United K; Kingdom is l;in Europe.;The United Kin;"
    When it is reassembled
    Then it should print
    """
    The United Kingdom is located in Europe.
    """
