<fieldset>
	<legend>Connexion</legend>
	<form action="login" method="post">
		<div><label for="email">Adresse email * : </label><input required value="${form['email']}" type="text" name="email" id="email"/><span>${errors['email']}</span></div>
		<div><label for="pwd">Mot de passe * : </label><input required value="${form['pwd']}" type="password" name="pwd" id="pwd"/><span>${errors['pwd']}</span></div>
		<div><input type="submit" value="Connexion"/></div>
		<div>${msgValidation}</div>
	</form>
</fieldset>