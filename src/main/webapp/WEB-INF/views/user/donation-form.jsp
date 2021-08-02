<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/includes/taglibs.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html lang="pl">
  <%@ include file="/WEB-INF/includes/header.jsp" %>

  <body>
    <header class="header--form-page">
      <%@ include file="/WEB-INF/includes/top-nav-options.jsp" %>

      <div class="slogan container container--90">
        <div class="slogan--item">
          <h1>
            Oddaj rzeczy, których już nie chcesz<br />
            <span class="uppercase">potrzebującym</span>
          </h1>

          <div class="slogan--steps">
            <div class="slogan--steps-title">Wystarczą 4 proste kroki:</div>
            <ul class="slogan--steps-boxes">
              <li>
                <div><em>1</em><span>Wybierz rzeczy</span></div>
              </li>
              <li>
                <div><em>2</em><span>Spakuj je w worki</span></div>
              </li>
              <li>
                <div><em>3</em><span>Wybierz fundację</span></div>
              </li>
              <li>
                <div><em>4</em><span>Zamów kuriera</span></div>
              </li>
            </ul>
          </div>
        </div>
      </div>
    </header>

    <section class="form--steps">
      <div class="form--steps-instructions">
        <div class="form--steps-container">
          <h3>Ważne!</h3>
          <p data-step="1" class="active">
            Uzupełnij szczegóły dotyczące Twoich rzeczy. Dzięki temu będziemy
            wiedzieć komu najlepiej je przekazać.
          </p>
          <p data-step="2">
            Uzupełnij szczegóły dotyczące Twoich rzeczy. Dzięki temu będziemy
            wiedzieć komu najlepiej je przekazać.
          </p>
          <p data-step="3">
           Wybierz jedną, do
            której trafi Twoja przesyłka.
          </p>
          <p data-step="4">Podaj adres oraz termin odbioru rzeczy.</p>
        </div>
      </div>

      <div class="form--steps-container">
        <div class="form--steps-counter">Krok <span>1</span>/4</div>

        <form:form action="/donations/add" method="post" modelAttribute="donation">
          <!-- STEP 1: class .active is switching steps -->
          <div data-step="1" class="active">
            <h3>Zaznacz co chcesz oddać:</h3>

            <c:forEach items="${categories}" var="cat" >
              <div class="form-group form-group--checkbox">
                <label>
                  <form:checkbox path="categories" value="${cat.id}"/>
                  <span class="checkbox"></span>
                  <span class="description">${cat.name}</span>
                </label>
              </div>
            </c:forEach>

            <div class="form-group form-group--buttons">
              <button type="button" class="btn next-step">Dalej</button>
            </div>
          </div>

          <!-- STEP 2 -->
          <div data-step="2" >
            <h3>Podaj liczbę 60l worków, w które spakowałeś/aś rzeczy:</h3>

            <div class="form-group form-group--inline">
              <label>
                Liczba 60l worków:
                <form:input type="number" path="quantity" step="1" min="1" />
              </label>
            </div>

            <div class="form-group form-group--buttons">
              <button type="button" class="btn prev-step">Wstecz</button>
              <button type="button" class="btn next-step">Dalej</button>
            </div>
          </div>

          <!-- STEP 4 -->
          <div data-step="3">
            <h3>Wybierz organizacje, której chcesz pomóc:</h3>

            <c:forEach items="${institutions}" var="inst" >
              <div class="form-group form-group--checkbox">
                <label>
                  <form:radiobutton path="institution" value="${inst.id}"/>
                  <span class="checkbox radio"></span>
                  <span class="description">
                    <div class="title">Fundacja "${inst.name}"</div>
                    <div class="subtitle">${inst.description}</div>
                  </span>
                </label>
              </div>
            </c:forEach>

            <div class="form-group form-group--buttons">
              <button type="button" class="btn prev-step">Wstecz</button>
              <button type="button" class="btn next-step">Dalej</button>
            </div>
          </div>

          <!-- STEP 5 -->
          <div data-step="4">
            <h3>Podaj adres oraz termin odbioru rzecz przez kuriera:</h3>

            <div class="form-section form-section--columns">
              <div class="form-section--column">
                <h4>Adres odbioru</h4>
                <div class="form-group form-group--inline">
                  <label> Ulica <form:input type="text" path="street" /> </label>
                </div>

                <div class="form-group form-group--inline">
                  <label> Miasto <form:input type="text" path="city" /> </label>
                </div>

                <div class="form-group form-group--inline">
                  <label>
                    Kod pocztowy <form:input type="text" path="zipCode" />
                  </label>
                </div>

                <div class="form-group form-group--inline">
                  <label>
                    Numer telefonu <input type="phone" name="phone" />
                  </label>
                </div>
              </div>

              <div class="form-section--column">
                <h4>Termin odbioru</h4>
                <div class="form-group form-group--inline">
                  <label> Data <form:input type="date" path="pickUpDate" /> </label>
                </div>

                <div class="form-group form-group--inline">
                  <label> Godzina <form:input type="time" path="pickUpTime" /> </label>
                </div>

                <div class="form-group form-group--inline">
                  <label>
                    Uwagi dla kuriera
                    <form:textarea path="pickUpComment" rows="5"></form:textarea>
                  </label>
                </div>
              </div>
            </div>
            <div class="form-group form-group--buttons">
              <button type="button" class="btn prev-step">Wstecz</button>
              <button type="button" class="btn next-step">Dalej</button>
            </div>
          </div>

          <!-- STEP 6 -->
          <div data-step="5">
            <h3>Podsumowanie Twojej darowizny</h3>

            <div class="summary">
              <div class="form-section">
                <h4>Oddajesz:</h4>
                <ul>
                  <li>
                    <span class="icon icon-bag"></span>
                    <span class="summary--text">4 worki ubrań w dobrym stanie dla dzieci</span>
                  </li>

                  <li>
                    <span class="icon icon-hand"></span>
                    <span class="summary--text">Dla fundacji "Mam marzenie" w Warszawie</span>
                  </li>
                </ul>
              </div>

              <div class="form-section form-section--columns">
                <div class="form-section--column">
                  <h4>Adres odbioru:</h4>
                  <ul>
                    <li id="confirm-street">Prosta 51</li>
                    <li id="confirm-city">Warszawa</li>
                    <li id="confirm-zipcode">99-098</li>
                    <li id="confirm-phone">123 456 789</li>
                  </ul>
                </div>

                <div class="form-section--column">
                  <h4>Termin odbioru:</h4>
                  <ul>
                    <li id="confirm-date">13/12/2018</li>
                    <li id="confirm-time">15:40</li>
                    <li id="confirm-comments">Brak uwag</li>
                  </ul>
                </div>
              </div>
            </div>

            <div class="form-group form-group--buttons">
              <button type="button" class="btn prev-step">Wstecz</button>
              <button type="submit" class="btn">Potwierdzam</button>
            </div>
          </div>
        </form:form>
      </div>
    </section>

    <%@ include file="/WEB-INF/includes/footer.jsp" %>
    <script src="<c:url value="/resources/js/app.js"/>"></script>
  </body>
</html>
