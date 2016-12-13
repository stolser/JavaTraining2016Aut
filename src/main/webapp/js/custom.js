var makeUnclickable = function () {
    // if( !event ) event = window.event;
    event.preventDefault();
};

$(document).ready(function () {
    console.log("jQuery is ready!");

    highlightSelectedMenu();

    activateUserAccountTabs();

    $(document).on("keypress", ":input:not(textarea)", function (event) {
        if (event.keyCode == 13) {
            event.preventDefault();
        }
    });

    $(".ajax-validated").focusout(function () {
        var $validatedElem = $(this);
        var paramName = $validatedElem.prop("name");
        var paramValue = $validatedElem.val();
        var entityOperationType = $("#entityOperationType").val();
        var entityId = $("#entityId").val();

        console.log("paramName = " + paramName + "; paramValue = " + paramValue +
            "entityId = " + entityId + "; entityOperationType = " + entityOperationType);

        $.post("/backend/validation", {
            'paramName': paramName,
            'paramValue': paramValue,
            'entityId': entityId,
            'entityOperationType': entityOperationType
        }, function (data) {
            console.log("a response from the server is here!");

            // var json = JSON.parse(data);
            var json = data;
            console.log("json = " + json);

            var statusCode = json["statusCode"];
            var message = json["validationMessage"];

            console.log("statusCode = " + statusCode);
            console.log("message = " + message);

            var cssClass = (statusCode == 200) ? "success" : "error";
            console.log("cssClass = " + cssClass);

            if (cssClass == "success") {
                $validatedElem.closest(".form-group").removeClass("has-error");
                $validatedElem.closest(".form-group").addClass("has-success");
            } else if (cssClass == "error") {
                $validatedElem.closest(".form-group").removeClass("has-success");
                $validatedElem.closest(".form-group").addClass("has-error");
            }

            $validatedElem.next(".messages").remove();
            $validatedElem.after("<label class='messages " + cssClass + "'>" + message +
                "</label>");

            activateOrDisableSubmitBtn($validatedElem);

        }, 'json');
    });

    $("input, textarea").not(".ajax-validated").focusout(function () {
        $(this).next(".messages").remove();
        activateOrDisableSubmitBtn($(this));
    });

    $("form select").change(function () {
        activateOrDisableSubmitBtn($(this));
    });

    $(".disabled").click(makeUnclickable);

});

function activateOrDisableSubmitBtn($thisInput) {
    console.log("$thisInput = " + $thisInput);
    var $thisForm = $thisInput.closest("form"); // the <form> element inside which this input resides;
    console.log("$thisForm = " + $thisForm);

    var $thisFormInputs = $thisForm.find("input");  // all the input in the current form;
    var $errorMsg = $thisFormInputs.nextAll(".messages.error"); // elements containing error-messages;

    var $submitBtn = $thisForm.find(":submit");
    console.log("$submitBtn = " + $submitBtn.html());

    if (($errorMsg.length == 0) // there is no error-messages;
        && ($thisFormInputs.filter(function () {
            // returns empty form-elements that reside inside elements with class='required';
            var $closest = $(this).closest(".required");
            console.log("$parent.length = " + $closest.length);

            return ($closest.length > 0) && ($(this).val() == "");
        }).length == 0)) {
        console.log("activating the submit btn...");

        activateElement($submitBtn);

    } else {
        console.log("Deactivating the submit btn...");

        disActivateElement($submitBtn);
    }
}

function activateElement(element) {
    // event = window.event;
    element.removeClass("disabled");
    element.addClass("active");
    element.unbind('click', makeUnclickable);

}

function disActivateElement(element) {
    element.removeClass("active");
    element.addClass("disabled");
    element.click(makeUnclickable);
}

function highlightSelectedMenu() {
    var pathname = $(location).attr('pathname');
    var $menuLinks = $(".navbar li > a");

    $menuLinks.removeClass('active');

    $menuLinks.filter(function () {
        return ($(this).attr('href') == pathname);
    }).parent("li").addClass('active');
}

function activateUserAccountTabs() {
    $(".userAccountTabs .nav-tabs > .active").removeClass('active');
    $(".userAccountTabs .nav-tabs > li").first().addClass('active');
    
    $(".userAccountTabs .tab-content .active").removeClass('active');
    $(".userAccountTabs .tab-content .tab-pane").first().addClass('active');
}


