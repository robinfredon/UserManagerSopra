<fieldset>
	<legend>Inscription</legend>
	<form action="formRegister" method="post">
	<p>Formulaire d'inscription, les champs (*) sont requis :</p>
		<div><label for="email">Adresse email * : </label><input required value="${form['email']}" type="text" name="email" id="email"/><span>${errors['email']}</span></div>
		<div><label for="pwd">Mot de passe * : </label><input required value="${form['pwd']}" type="password" name="pwd" id="pwd"/><span>${errors['pwd']}</span></div>
		<div><label for="cpwd">Confirmation du mot de passe * : </label><input required value="${form['cpwd']}" type="password" name="cpwd" id="cpwd"/><span>${errors['pwd']}</span></div>
		<div><label for="name">Nom d'utilisateur : </label><input type="text" value="${form['name']}" name="name" id="name"/><span>${errors['name']}</span></div>		
		<div><input type="submit" value="Enregistrement"/></div>
			<div>${msgValidation}</div>
	</form>
</fieldset>