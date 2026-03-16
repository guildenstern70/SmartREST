<#-- @ftlvariable name="version" type="java.lang.String" -->
<!DOCTYPE HTML>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <meta name="robots" content="noindex,nofollow"/>
    <meta name="googlebot" content="noindex"/>
    <title>SMART REST - Spring Boot API Template</title>
    <script>
        (function () {
            var storedTheme = localStorage.getItem("smartrest-theme");
            var prefersDark = window.matchMedia("(prefers-color-scheme: dark)").matches;
            var shouldUseDark = storedTheme ? storedTheme === "dark" : prefersDark;
            if (shouldUseDark) {
                document.documentElement.classList.add("dark");
            }
        })();
    </script>
    <script src="https://cdn.tailwindcss.com"></script>
    <script>
        tailwind.config = {
            darkMode: "class",
            theme: {
                extend: {
                    colors: {
                        brand: {
                            500: "#2563eb",
                            600: "#1d4ed8"
                        }
                    }
                }
            }
        }
    </script>
</head>
<body class="min-h-screen bg-slate-100 text-slate-900 antialiased transition-colors dark:bg-slate-950 dark:text-slate-100">
<main class="mx-auto flex min-h-screen max-w-6xl items-center px-6 py-12 lg:px-8">
    <section class="w-full overflow-hidden rounded-3xl border border-slate-200 bg-white shadow-2xl shadow-slate-200/70 dark:border-slate-800 dark:bg-slate-900/80 dark:shadow-black/30">
        <div class="grid gap-10 p-8 md:p-12 lg:grid-cols-2 lg:gap-14 lg:p-14">
            <div class="space-y-6">
                <div class="flex items-center justify-between gap-4">
                    <span class="inline-flex items-center rounded-full border border-slate-300 bg-slate-100 px-3 py-1 text-xs font-semibold uppercase tracking-widest text-slate-600 dark:border-slate-700 dark:bg-slate-800 dark:text-slate-300">
                    Production-ready template
                    </span>
                    <button
                        id="theme-toggle"
                        type="button"
                        aria-label="Toggle color theme"
                        aria-pressed="false"
                        class="inline-flex items-center gap-2 rounded-lg border border-slate-300 bg-white px-3 py-2 text-sm font-medium text-slate-700 transition hover:bg-slate-100 focus:outline-none focus:ring-2 focus:ring-brand-500 focus:ring-offset-2 dark:border-slate-700 dark:bg-slate-800 dark:text-slate-200 dark:hover:bg-slate-700 dark:focus:ring-offset-slate-900"
                    >
                        <span id="theme-icon" aria-hidden="true">Light</span>
                        <span class="text-xs text-slate-500 dark:text-slate-400">mode</span>
                    </button>
                </div>
                <div class="space-y-4">
                    <h1 class="text-4xl font-extrabold tracking-tight text-slate-900 sm:text-5xl dark:text-white">SmartREST</h1>
                    <p class="max-w-xl text-base leading-relaxed text-slate-600 sm:text-lg dark:text-slate-300">
                        A modern Spring Boot + Kotlin starter for REST APIs with DTO mapping, JPA persistence,
                        Freemarker web entrypoint, and local tooling for fast development.
                    </p>
                </div>
                <div class="inline-flex items-center rounded-lg border border-slate-300 bg-slate-100 px-3 py-2 text-sm text-slate-600 dark:border-slate-700 dark:bg-slate-800/70 dark:text-slate-300">
                    Smart REST version <span id="version" class="ml-2 font-semibold text-slate-900 dark:text-white">${version}</span>
                </div>
            </div>

            <div class="grid gap-4 self-center">
                <a
                    class="group rounded-xl border border-brand-500/40 bg-brand-600 px-5 py-4 font-semibold text-white transition hover:bg-brand-500 focus:outline-none focus:ring-2 focus:ring-brand-500 focus:ring-offset-2 focus:ring-offset-white dark:focus:ring-offset-slate-900"
                    href="/swagger-ui/index.html"
                    role="button"
                >
                    API Documentation
                    <span class="ml-2 opacity-80 transition group-hover:translate-x-0.5 group-hover:opacity-100">-></span>
                </a>

                <a
                    class="rounded-xl border border-slate-300 bg-white px-5 py-4 font-semibold text-slate-800 transition hover:border-slate-400 hover:bg-slate-100 focus:outline-none focus:ring-2 focus:ring-slate-500 focus:ring-offset-2 focus:ring-offset-white dark:border-slate-700 dark:bg-slate-800 dark:text-slate-100 dark:hover:border-slate-500 dark:hover:bg-slate-700 dark:focus:ring-offset-slate-900"
                    href="/api/v1/catalog/openapi/download"
                    role="button"
                >
                    Download OpenAPI Catalog
                </a>

                <a
                    class="rounded-xl border border-emerald-500/40 bg-emerald-600 px-5 py-4 font-semibold text-white transition hover:bg-emerald-500 focus:outline-none focus:ring-2 focus:ring-emerald-500 focus:ring-offset-2 focus:ring-offset-white dark:focus:ring-offset-slate-900"
                    href="/h2"
                    role="button"
                >
                    H2 Database Console
                </a>

                <p class="pt-2 text-sm leading-relaxed text-slate-500 dark:text-slate-400">
                    Quick access to API docs, downloadable service catalog, and in-memory database console.
                </p>
            </div>
        </div>
    </section>
</main>
<script>
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
</script>
</body>

</html>