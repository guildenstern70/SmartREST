<#-- @ftlvariable name="version" type="java.lang.String" -->
<!DOCTYPE HTML>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <meta name="robots" content="noindex,nofollow"/>
    <meta name="googlebot" content="noindex"/>
    <title>SmartREST - Spring Boot API Template</title>
    <script src="/js/theme-init.js"></script>
    <script src="https://cdn.tailwindcss.com"></script>
    <script src="/js/tailwind-config.js"></script>
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
                    SmartREST version <span id="version" class="ml-2 font-semibold text-slate-900 dark:text-white">${version}</span>
                </div>
            </div>

            <div class="grid gap-4 self-center">
                <a
                    class="group inline-flex items-center gap-3 rounded-xl border border-brand-500/40 bg-brand-600 px-5 py-4 font-semibold text-white transition hover:bg-brand-500 focus:outline-none focus:ring-2 focus:ring-brand-500 focus:ring-offset-2 focus:ring-offset-white dark:focus:ring-offset-slate-900"
                    href="/swagger-ui/index.html"
                    role="button"
                >
                    <span>API Documentation</span>
                    <svg aria-hidden="true" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" class="h-5 w-5 opacity-90 transition group-hover:translate-x-0.5 group-hover:opacity-100">
                        <path stroke-linecap="round" stroke-linejoin="round" d="M5 12h14m-6-6 6 6-6 6"/>
                    </svg>
                </a>

                <a
                    class="inline-flex items-center gap-3 rounded-xl border border-slate-300 bg-white px-5 py-4 font-semibold text-slate-800 transition hover:border-slate-400 hover:bg-slate-100 focus:outline-none focus:ring-2 focus:ring-slate-500 focus:ring-offset-2 focus:ring-offset-white dark:border-slate-700 dark:bg-slate-800 dark:text-slate-100 dark:hover:border-slate-500 dark:hover:bg-slate-700 dark:focus:ring-offset-slate-900"
                    href="/api/v1/catalog/openapi/download"
                    role="button"
                >
                    <svg aria-hidden="true" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" class="h-5 w-5">
                        <path stroke-linecap="round" stroke-linejoin="round" d="M12 4v10m0 0 4-4m-4 4-4-4M5 20h14"/>
                    </svg>
                    <span>Download OpenAPI Catalog</span>
                </a>

                <a
                    class="inline-flex items-center gap-3 rounded-xl border border-emerald-500/40 bg-emerald-600 px-5 py-4 font-semibold text-white transition hover:bg-emerald-500 focus:outline-none focus:ring-2 focus:ring-emerald-500 focus:ring-offset-2 focus:ring-offset-white dark:focus:ring-offset-slate-900"
                    href="/h2"
                    role="button"
                >
                    <svg aria-hidden="true" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" class="h-5 w-5">
                        <rect x="3" y="4" width="18" height="16" rx="2" ry="2"/>
                        <path stroke-linecap="round" stroke-linejoin="round" d="m7 10 3 2-3 2m5 0h5"/>
                    </svg>
                    <span>H2 Database Console</span>
                </a>

                <p class="pt-2 text-sm leading-relaxed text-slate-500 dark:text-slate-400">
                    Quick access to API docs, downloadable service catalog, and in-memory database console.
                </p>
            </div>
        </div>
    </section>
</main>
<script src="/js/theme-toggle.js"></script>
</body>

</html>