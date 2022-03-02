<div class="game">
    <div class="game-element">
        <% String table=(String)request.getAttribute("map"); if (table !=null) { out.println(table); } %>
    </div>
    <c:if test="${ !empty requestScope.map }">
        <div class="game-element">
            <h3 class="title-1">Choisissez votre mouvement</h3>
            <form class="form-layer" action="game" method="post">
                <div class="game-form-group">
                    <p>A :</p>
                    <input class="label-game" type="radio" id="north" name="A" value="north">
                    <label class="label-game" for="north">Haut</label>
                    <input class="label-game" type="radio" id="south" name="A" value="south">
                    <label class="label-game" for="south">Bas</label>
                    <input class="label-game" type="radio" id="east" name="A" value="east">
                    <label class="label-game" for="east">Droite</label>
                    <input class="label-game" type="radio" id="west" name="A" value="west">
                    <label class="label-game" for="west">Gauche</label>
                </div>
                <div class="game-form-group">
                    <p>A :</p>
                    <input class="label-game" type="radio" name="B" value="north">
                    <label class="label-game" for="north">Haut</label>
                    <input class="label-game" type="radio" name="B" value="south">
                    <label class="label-game" for="south">Bas</label>
                    <input class="label-game" type="radio" name="B" value="east">
                    <label class="label-game" for="east">Droite</label>
                    <input class="label-game" type="radio" name="B" value="west">
                    <label class="label-game" for="west">Gauche</label>
                </div>
                <div class="game-form-group">
                    <p>C :</p>
                    <input class="label-game" type="radio" name="C" value="north">
                    <label class="label-game" for="north">Haut</label>
                    <input class="label-game" type="radio" name="C" value="south">
                    <label class="label-game" for="south">Bas</label>
                    <input class="label-game" type="radio" name="C" value="east">
                    <label class="label-game" for="east">Droite</label>
                    <input class="label-game" type="radio" name="C" value="west">
                    <label class="label-game" for="west">Gauche</label>
                </div>
                <div class="game-form-group">
                    <p>D :</p>
                    <input class="label-game" type="radio" name="D" value="north">
                    <label class="label-game" for="north">Haut</label>
                    <input class="label-game" type="radio" name="D" value="south">
                    <label class="label-game" for="south">Bas</label>
                    <input class="label-game" type="radio" name="D" value="east">
                    <label class="label-game" for="east">Droite</label>
                    <input class="label-game" type="radio" name="D" value="west">
                    <label class="label-game" for="west">Gauche</label>
                </div>
                <div class="form-group">
                    <button type="submit">Déplacer</button>
                </div>
            </form>
        </div>
    </c:if>
</div>