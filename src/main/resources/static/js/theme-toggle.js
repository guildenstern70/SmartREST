//
// Project SmartREST
// Copyright (c) Alessio Saltarin 2022-26
// This software is licensed under MIT License (see LICENSE)
//
(function () {
    var storageKey = "smartrest-theme";
    var root = document.documentElement;
    var button = document.getElementById("theme-toggle");
    var icon = document.getElementById("theme-icon");

    function isDark() {
        return root.classList.contains("dark");
    }

    function render() {
        var dark = isDark();
        button.setAttribute("aria-pressed", String(dark));
        icon.textContent = dark ? "Dark" : "Light";
    }

    button.addEventListener("click", function () {
        var nextDark = !isDark();
        root.classList.toggle("dark", nextDark);
        localStorage.setItem(storageKey, nextDark ? "dark" : "light");
        render();
    });

    render();
})();

