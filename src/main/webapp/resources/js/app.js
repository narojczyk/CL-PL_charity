document.addEventListener("DOMContentLoaded", function() {

  /**
   * Form Select
   */
  class FormSelect {
    constructor($el) {
      this.$el = $el;
      this.options = [...$el.children];
      this.init();
    }

    init() {
      this.createElements();
      this.addEvents();
      this.$el.parentElement.removeChild(this.$el);
    }

    createElements() {
      // Input for value
      this.valueInput = document.createElement("input");
      this.valueInput.type = "text";
      this.valueInput.name = this.$el.name;

      // Dropdown container
      this.dropdown = document.createElement("div");
      this.dropdown.classList.add("dropdown");

      // List container
      this.ul = document.createElement("ul");

      // All list options
      this.options.forEach((el, i) => {
        const li = document.createElement("li");
        li.dataset.value = el.value;
        li.innerText = el.innerText;

        if (i === 0) {
          // First clickable option
          this.current = document.createElement("div");
          this.current.innerText = el.innerText;
          this.dropdown.appendChild(this.current);
          this.valueInput.value = el.value;
          li.classList.add("selected");
        }

        this.ul.appendChild(li);
      });

      this.dropdown.appendChild(this.ul);
      this.dropdown.appendChild(this.valueInput);
      this.$el.parentElement.appendChild(this.dropdown);
    }

    addEvents() {
      this.dropdown.addEventListener("click", e => {
        const target = e.target;
        this.dropdown.classList.toggle("selecting");

        // Save new value only when clicked on li
        if (target.tagName === "LI") {
          this.valueInput.value = target.dataset.value;
          this.current.innerText = target.innerText;
        }
      });
    }
  }
  document.querySelectorAll(".form-group--dropdown select").forEach(el => {
    new FormSelect(el);
  });

  /**
   * Hide elements when clicked on document
   */
  document.addEventListener("click", function(e) {
    const target = e.target;
    const tagName = target.tagName;

    if (target.classList.contains("dropdown")) return false;

    if (tagName === "LI" && target.parentElement.parentElement.classList.contains("dropdown")) {
      return false;
    }

    if (tagName === "DIV" && target.parentElement.classList.contains("dropdown")) {
      return false;
    }

    document.querySelectorAll(".form-group--dropdown .dropdown").forEach(el => {
      el.classList.remove("selecting");
    });
  });

  /**
   * Switching between form steps
   */
  class FormSteps {
    constructor(form) {
      this.$form = form;
      this.$next = form.querySelectorAll(".next-step");
      this.$prev = form.querySelectorAll(".prev-step");
      this.$step = form.querySelector(".form--steps-counter span");
      this.currentStep = 1;

      this.$stepInstructions = form.querySelectorAll(".form--steps-instructions p");
      const $stepForms = form.querySelectorAll("form > div");
      this.slides = [...this.$stepInstructions, ...$stepForms];

      this.init();
    }

    /**
     * Init all methods
     */
    init() {
      this.events();
      this.updateForm();
    }

    /**
     * All events that are happening in form
     */
    events() {
      // Next step
      this.$next.forEach(btn => {
        btn.addEventListener("click", e => {
          e.preventDefault();
          this.currentStep++;
          this.updateForm();
        });
      });

      // Previous step
      this.$prev.forEach(btn => {
        btn.addEventListener("click", e => {
          e.preventDefault();
          this.currentStep--;
          this.updateForm();
        });
      });

      // Form submit
      this.$form.querySelector("form").addEventListener("submit", e => this.submit(e));
    }

    /**
     * Update form front-end
     * Show next or previous section etc.
     */
    updateForm() {
      this.$step.innerText = this.currentStep;

      // TODO: Validation

      this.slides.forEach(slide => {
        slide.classList.remove("active");

        if (slide.dataset.step == this.currentStep) {
          slide.classList.add("active");
        }
      });

      this.$stepInstructions[0].parentElement.parentElement.hidden = this.currentStep >= 5;
      this.$step.parentElement.hidden = this.currentStep >= 5;

      // TODO: get data from inputs and show them in summary
      var divs = form.querySelectorAll("div");
      // var inputData = new Array();
      var categories = new Array();
      var bags = 0;
      var foundation = "";
      var street = "";
      var city = "";
      var zipCode = "";
      var pickDate = "";
      var pickTime = "";
      var pickComment = "";
      var phone = "";
      divs.forEach(d=>{
        if (d.dataset.step !== null) {
          if (d.dataset.step == 1) {
            // console.log(d.dataset.step);
            // console.log(d)
            var labels = d.querySelectorAll("label")
            labels.forEach(l=>{
              var input = l.querySelector("input")
              if(input.checked === true){
                // console.log(l.querySelector(".description").innerText)
                categories.push(l.querySelector(".description").innerText);
              }
            })
            // console.log(categories);
          }

          if (d.dataset.step == 2) {
            // console.log(d.dataset.step);
            // console.log(d);
            var input = d.querySelector("input");
            bags = input.value;
            // console.log(bags);
          }

          if (d.dataset.step == 3) {
            // console.log(d.dataset.step);
            // console.log(d)
            var labels = d.querySelectorAll("label")
            labels.forEach(l=>{
              var input = l.querySelector("input")
              if(input.checked === true){
                foundation = l.querySelector(".title")
                    .innerText.replace('Fundacja', 'Dla fundacji:');
              }
            })
            // console.log(foundation);
          }

          if (d.dataset.step == 4) {
            // console.log(d.dataset.step);
            // console.log(d)
            street = d.querySelector("#street").value;
            city = d.querySelector("#city").value;
            zipCode = d.querySelector("#zipCode").value;
            // phone = d.querySelector("#phone").value;
            pickDate = d.querySelector("#pickUpDate").value;
            pickTime = d.querySelector("#pickUpTime").value;
            pickComment = d.querySelector("#pickUpComment").value;

            // console.log("str "+ street);
            // console.log("cit "+ city);
            // console.log("zip "+ zipCode);
            // console.log("dat "+ pickDate);
            // console.log("tim "+ pickTime);
            // console.log("com "+ pickComment);
          }

          if (d.dataset.step == 5) {
            // console.log(d.dataset.step);
            // console.log(d)

            var joinCategories = "";
            categories.forEach(c=>{
              joinCategories += c + ", ";
            })

            var bagSpan =  d.querySelector(".icon-bag").nextElementSibling;
            bagSpan.innerHTML = bags + " worki: " + joinCategories.replace(/..$/,".");

            d.querySelector(".icon-hand").nextElementSibling.innerHTML = foundation;
            // console.log(bagSpan);
            // console.log(d.querySelector(".icon-hand").nextElementSibling);
            d.querySelector("#confirm-street").innerHTML = street;
            d.querySelector("#confirm-city").innerHTML = city;
            d.querySelector("#confirm-zipcode").innerHTML = zipCode;
            d.querySelector("#confirm-phone").innerHTML = "phone n/a";
            d.querySelector("#confirm-date").innerHTML = pickDate;
            d.querySelector("#confirm-time").innerHTML = pickTime;
            d.querySelector("#confirm-comments").innerHTML = "Uwagi: " + pickComment;
            // console.log(foundation);
          }
        }
      })
    }
  }
  const form = document.querySelector(".form--steps");
  if (form !== null) {
    new FormSteps(form);
  }
});
