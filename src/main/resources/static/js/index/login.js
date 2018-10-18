let facebookLogin = {
    logout() {
        $.post("/logout", function() {
            $("#user").html('');
            $(".unauthenticated").show();
        });
        return true;
    }
};

$.ajaxSetup({
    beforeSend : function(xhr, settings) {
        if (settings.type === 'POST' || settings.type === 'PUT'
            || settings.type === 'DELETE') {
            if (!(/^http:.*/.test(settings.url) || /^https:.*/
                .test(settings.url))) {
                xhr.setRequestHeader("X-XSRF-TOKEN",
                    Cookies.get('XSRF-TOKEN'));
            }
        }
    }
});

$.get("/user", function(data) {
    let loginContainer = $(".containerUnauthenticated");
    let actionBar = $("#actionBar");
    if(data) {
        loginContainer.hide();
        actionBar.show();
    }
    else {
        loginContainer.show();
        actionBar.hide();
    }
});


