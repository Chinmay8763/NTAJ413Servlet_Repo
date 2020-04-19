function validate(frm)
{
   //set vflag to "yes" indicating client side from valudations are done
	frm.vflag.value="yes";
	//set styles to error messages
	document.getElementById("nameErr").innerHTML="";
	document.getElementById("ageErr").innerHTML="";
	document.getElementById("nameErr").style="color:red";
	document.getElementById("ageErr").style="color:red";
	
	//read form data
	var name=frm.pname.value;
	var age=frm.page.value;
	//perform client side form validations
	if(name=="")//required rule
		{
		document.getElementById("nameErr").innerHTML="Person Name should be manadatory";
		frm.pname.focus();
		return false;
		}//if
	if(age=="")//required rule
		{
		document.getElementById("nameErr").InnerHTML="Person Age id manadatory";
		frm.page.focus();
		return false;
		}//if
	else
		{
		if(isNaN(age))//checks weather age is number value or not
			{
			document.getElementById("nameErr").innerHTML="Person age must be numeric value";
			frm.page.focus();
			frm.page.value="";
			return false;
			}//if
		}//else
	return true;
		}//function