<%@page contentType="text/html" pageEncoding="UTF-8" %>

    <html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="css/styles.css">
        <title>Hello</title>
    </head>

    <body>
        <div class="container">
            <h1 class="title-1">Hello on index page</h1>
            <div class="form-box">
                <h3 class="title-1">Créer un compte</h3>
                <form class="form-layer" action="register" method="post">
                    <div class="form-group">
                        <label for="login">Nom:</label>
                        <input type="text" name="nom" id="">
                    </div>
                    <div class="form-group">
                        <label for="login">Prenom:</label>
                        <input type="text" name="prenom" id="">
                    </div>
                    <div class="form-group">
                        <label for="login">Age:</label>
                        <input type="text" name="age" id="">
                    </div>
                    <div class="form-group">
                        <label for="login">Login:</label>
                        <input type="text" name="login" id="">
                    </div>
                    <div class="form-group">
                        <label for="password">Password:</label>
                        <input type="text" name="motdepasse" id="">
                    </div>
                    <div class="form-group">
                        <button type="submit">S'enregistrer</button>
                    </div>
                </form>
            </div>
            <div>
                <a href="index"><button>Retour</button></a>
            </div>
        </div>
    </body>

    </html>