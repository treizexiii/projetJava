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
            <c:choose>
                <c:when test="${ empty sessionScope.joueur }">
                    <h3 class="title-1">se connecter</h3>
                    <c:if test="${ !empty error}">
                        <div>
                            <p class="error-message">
                                <c:out value="${ error }" />
                            </p>
                        </div>
                    </c:if>
                    <form class="form-layer" action="login" method="post">
                        <div class="form-group">
                            <label for="login">Login:</label>
                            <input type="text" name="login" id="">
                        </div>
                        <div class="form-group">
                            <label for="password">Password:</label>
                            <input type="text" name="password" id="">
                        </div>
                        <div class="form-group">
                            <button type="submit">Se connecter</button>
                        </div>
                    </form>
                    <p><a href="register-form">Pas de compte?</a></p>
                </c:when>
                <c:otherwise>
                    <div>
                        <%@ include file="html/menu.jsp" %>
                            <c:if test="${ !empty sessionScope.joueur}">
                                <c:if test="${ !empty sessionScope.game}">
                                    <%@ include file="html/game.jsp" %>
                                </c:if>
                            </c:if>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</body>

</html>