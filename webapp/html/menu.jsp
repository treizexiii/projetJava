<%@page import="models.Compte" %>
<%@page import="java.util.ArrayList" %>

<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/styles.css">
    <title>Jeu</title>
</head>

<body>
    <%Compte joueur=(Compte)request.getAttribute("joueur");%>
    <div class="container">
        <h1 class="title-1">Jeu</h1>
        <div class="form-box">
            <h3 class="title-1">Menu</h3>
            <div>
            
                <p>Hello <c:out value="Bounjour ! "/></p>
            </div>
        </div>
    </div>
</body>

</html>
