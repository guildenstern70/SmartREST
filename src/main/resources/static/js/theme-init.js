//
// Project SmartREST
// Copyright (c) Alessio Saltarin 2022-26
// This software is licensed under MIT License (see LICENSE)
//
(function () {
    var storedTheme = localStorage.getItem("smartrest-theme");
    var prefersDark = window.matchMedia("(prefers-color-scheme: dark)").matches;
    var shouldUseDark = storedTheme ? storedTheme === "dark" : prefersDark;
    if (shouldUseDark) {
        document.documentElement.classList.add("dark");
    }
})();

