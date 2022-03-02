<c:if test="${ !empty sessionScope.joueur }">
    <h2 class="title-1">Jeu</h2>
    <div class="form-box">
        <h3 class="title-1">Menu</h3>
        <div>
            <p>Hello ${ sessionScope.joueur }</p>
            <c:if test="${ !empty error}">
                <div>
                    <p class="error-message">
                        <c:out value="${ error }" />
                    </p>
                </div>
            </c:if>
        </div>
        <div class="menu">
            <c:choose>
                <c:when test="${ empty sessionScope.isRunning }">
                    <a href="game"><button class="btn-menu">Commencer une partie</button></a>
                </c:when>
                <c:when test="${ !empty sessionScope.isRunning }">
                    <a href="game"><button class="btn-menu">Arreter la partie</button></a>
                </c:when>
            </c:choose>
            <a href="logout"><button class="btn-menu">Se deconnecter</button></a>
        </div>
        <div>
            <c:if test="${ (!empty sessionScope.isRunning) and (empty requestScope.map) }">
                <div class="form-box">
                    <form class="form-layer" action="game" enctype="multipart/form-data" method="post">
                        <div class="form-group">
                            <label for="login">Charger une carte:</label>
                            <input type="file" name="game-map" id="">
                            <input type="hidden" name="MAX_FILE_SIZE" value="10485760" />
                        </div>
                        <div class="form-group">
                            <button type="submit">Charger!</button>
                    </form>
                </div>
            </c:if>
        </div>
    </div>
</c:if>