/*function signup() {
    var username = $('#signup_username').val();
    var password = $('#signup_password').val();
    var email = $('#signup_email').val();

    $.ajax({
            contentType: "application/json",
            type: 'POST',
            url: "/dsaApp/user/addUser/"+username+"/"+password+"/"+email,
            data: JSON.stringify({username: username, password: password, email: email}),
            dataType: 'json',
            success: function (result) {
                alert("Sign up completed. Nice to meet you, " + username);
                localStorage.setItem("activeUser", username);
                window.location.href = "userProfile.html";
            },
            error : function(error) {
                if (username == "" || password == "" || email == "")
                    alert("You left something blank. Please try again!");
                else
                    alert("Username or email already in use. Please try again!");
                //window.location.href = "signup.html";
            }
    });
}

function login() {
    var username = $('#login_username').val();
    var password = $('#login_password').val();

    window.alert("Entraste en js login()");

     $.ajax({
            contentType: "application/json",
            type: 'POST',
            url: "/dsaApp/user/logIn/"+username+"/"+password,
            data: JSON.stringify({name: username, password: password}),
            dataType: 'json',
            success: function (result) {
                alert("Login successful. Welcome back " + username);
                localStorage.setItem("activeUser", username);
                window.location.href = "userProfile.html";
            },
            error : function(error) {
                if (username == "" || password == "")
                    alert("You left something blank, please try again!");
                else
                    alert("Wrong Username or Password, please try again!");
                //window.location.href = "login.html";
            }
     });
}

function logout() {
    localStorage.setItem("activeUser", null);
    alert('Come back soon!');
    window.location.href = "index.html";
}

function updateUser() {
    var oldUsername = localStorage.getItem("activeUser");
    var username = $('#update_username').val();
    var password = $('#update_password').val();
    var email = $('#update_email').val();

    $.ajax({
            contentType: "application/json",
            type: 'PUT',
            url: "/dsaApp/user/update/"+oldUsername+"/"+username+"/"+password+"/"+email,
            data: JSON.stringify({oldUsername: oldUsername, username: username, password: password, email: email}),
            dataType: 'json',
            success: function (result) {
                alert("Information updated successfully");
                localStorage.setItem("activeUser", username);
                window.location.href = "userProfile.html";
            },
            error : function(error) {
                if (username == "" || password == "" || email == "")
                    alert("You left something blank. Please try again!");
                else
                    alert("Username or email already in use. Please try again!");
                    window.location.href = "userProfile.html";
            }
    });
}

function deleteUser() {
    var user = localStorage.getItem("activeUser");

    $.ajax({
            contentType: "application/json",
            type: 'DELETE',
            url: "/dsaApp/user/"+username,
            data: JSON.stringify({username: username}),
            dataType: 'json',
            success: function (result) {
                alert("User deleted successfully. We are sad to see you go :(");
                localStorage.setItem("activeUser", null);
                window.location.href = "index.html";
            },
            error : function(error) {
                alert("User could not be deleted. Please try again later!");
                window.location.href = "userProfile.html";
            }
    });
}

// INCOMPLETE
function setUpStore() {
    $.ajax({
            contentType: "application/json",
            type: 'GET',
            url: "/dsaApp/storeList/"+item+"/"+username,
            data: JSON.stringify({item: item, username: username}),
            dataType: 'json',
            success: function (result) {
                // tallar llista i crear tenda
            },
            error : function(error) {
                 alert('Store set up failed. Please try again later!');
            }
    });
}

//INCOMPLETE
function buyItem() {
    var user = localStorage.getItem("activeUser");
    //var item = el item que hagi seleccionat

    $.ajax({
            contentType: "application/json",
            type: 'PUT',
            url: "/dsaApp/item/buyItem/"+item+"/"+username,
            data: JSON.stringify({item: item, username: username}),
            dataType: 'json',
            success: function (result) {
                alert("Successfully purchased " + item);
            },
            error : function(error) {
                 alert('Purchase failed. Might not have enough coins or already have it');
            }
    });
}

//INCOMPLETE
function getInventory() {
    var username = localStorage.getItem("activeUser");

    $.ajax({
            contentType: "application/json",
            type: 'GET',
            url: "/dsaApp/item/inventoryList/" + username,
            data: JSON.stringify({username: username}),
            dataType: 'json',
            success: function (result) {
                //tallar llista i visualització
            },
            error : function(error) {
                 alert('Could not fetch inventory. Please try again later!');
            }
    });
}*/