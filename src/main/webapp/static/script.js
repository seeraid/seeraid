// Custom scripts goes here
(function() {
    // Affix
    affixInit();
})();

function trackOutboundLink(link, category, action, label) { 
	trackOutboundLinkNoRediction(category, action, label); 
	setTimeout(function() {
		document.location.href = link.href;
	}, 100);
}
function trackOutboundLinkNoRediction(category, action, label) { 
	try {
		_gaq.push(['_trackEvent', category , action, label]);
	} catch(err){}	 
}
function showNewslettersModal() {
	$('#newslettersModal').modal();
}

function affixInit() {
    $('.docs-sidebar-nav').affix({
        offset: {
            top: 0,
            bottom:360
        }
    });
}

function setupLoginForm() {
    $('#email').tooltip( {
        title: 'Invalid email address',
        placement: 'right',
        trigger: 'manual'
    });

    $('#password').tooltip( {
        title: 'You must enter a password',
        placement: 'right',
        trigger: 'manual'
    });

    $('#login-form').submit( function() {
        var validForm = true;
        // validate email
        if( validateEmail( $('#email').val() ) == false ) {
            $('#email').tooltip('show');
            validForm = false;
        }
        else {
            $('#email').tooltip('hide');
        }
        // check for password
        if( $('#password').val() == '' ) {
            $('#password').tooltip('show');
            validForm = false;
        }
        else {
            $('#password').tooltip('hide');
        }

        if( validForm ) {
            message( 'error', 'Ooops', "Access Denied", 3000 );
        }
        return false;
    });
}

function setupResetForm() {
    $('#email').tooltip( {
        title: 'Invalid email address',
        placement: 'right',
        trigger: 'manual'
    });

    $('#reset-form').submit( function() {
        // validate email
        if( validateEmail( $('#email').val() ) == false ) {
            $('#email').tooltip('show');
        }
        else {
            $('#email').tooltip('hide');
            message( 'success', 'Check your mailbox', "We have reset your password", 3000 );
        }

        return false;
    });
}

function setupSignupForm() {
    $('#email').tooltip( {
        title: 'Invalid email address',
        placement: 'right',
        trigger: 'manual'
    });

    $('#password, #password-confirm').tooltip( {
        title: 'You must enter a password',
        placement: 'right',
        trigger: 'manual'
    });

    $('#signup-form').submit( function() {
        var validForm = true;
        // validate email
        if( validateEmail( $('#email').val() ) == false ) {
            $('#email').tooltip('show');
            validForm = false;
        }
        else {
            $('#email').tooltip('hide');
        }
        // check for password
        if( $('#password').val() == '' ) {
            $('#password').tooltip('show');
            validForm = false;
        }
        else {
            $('#password').tooltip('hide');
        }

        if( $('#password-confirm').val() == '' ) {
            $('#password-confirm').tooltip('show');
            validForm = false;
        }
        else {
            $('#password-confirm').tooltip('hide');
        }

        if( $('#password-confirm').val() != $('#password').val() ) {
            message( 'error', 'Passwords dont match', "You must enter the same password.", 3000 );
            validForm = false;
        }

        if( validForm ) {
            message( 'success', 'Thanks', "You have signed up please confirm your account.", 3000 );
        }
        return false;
    });
}

function validateEmail(email) {
    var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return re.test(email);
}

function message( type, title, content, duration ) {
    var messageHTML = '<div class="alert alert-' + type + '">'
                      + '<button type="button" class="close" data-dismiss="alert">x</button>'
                      + '<strong>' + title + '</strong> '
                      + content
                      + '</div>';

    var message = $(messageHTML).hide();

    $('#messages').append(message);

    message.fadeIn();

      // Increase compatibility with unnamed functions
    setTimeout(function() {
        message.fadeOut();
    }, duration);  // will work with every browser
}
;
