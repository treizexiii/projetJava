<%@page contentType="text/html" pageEncoding="UTF-8" %>

<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" href="css/styles.css" />
    <title>Hello</title>
  </head>

  <body>
    <div class="container">
      <h1 class="title-1">Projet NT2 CNAM</h1>
      <p><a href="index">Retour</a></p>
      <h3 class="title-1">Scores des differents joueurs</h3>
      <div class="form-box">
        <table class="scores">
          <thead>
            <tr>
              <th>Nom</th>
              <th>Prenom</th>
              <th>Nombre de parties gagnées</th>
              <th>Nombre de parties perdues</th>
            </tr>
          </thead>
          <tbody>
            <c:forEach items="${ requestScope.scores }" var="score">
              <tr>
                <td><c:out value="${ score.getLogin() }" /></td>
                <td>...</td>
                <td><c:out value="${ score.getNbPartiesGagnees() }" /></td>
                <td><c:out value="${ score.getNbPartiesPerdues() }" /></td>
              </tr>
            </c:forEach>
          </tbody>
        </table>
      </div>
    </div>
  </body>
</html>
