// 🚀 App Loaded
console.log("E-Commerce App Loaded 🚀");

// 🔥 Button click animation
document.addEventListener("DOMContentLoaded", function () {

    const buttons = document.querySelectorAll(".btn");

    buttons.forEach(btn => {
        btn.addEventListener("click", () => {
            btn.style.transform = "scale(0.95)";
            setTimeout(() => {
                btn.style.transform = "scale(1)";
            }, 150);
        });
    });

    // 🔥 Table row hover highlight
    const rows = document.querySelectorAll("table tr");

    rows.forEach(row => {
        row.addEventListener("mouseenter", () => {
            row.style.background = "rgba(255,255,255,0.2)";
        });

        row.addEventListener("mouseleave", () => {
            row.style.background = "rgba(255,255,255,0.1)";
        });
    });

});