const clocks = document.getElementsByClassName("clock");

function updateClocks() {
    for (let clock of clocks) {
        let timezone = clock.dataset.timezone;
        let time = new Date().toLocaleTimeString("en-US", {
            hour: '2-digit',
            minute: '2-digit',
            timeZone: timezone
        });
        clock.textContent = time;
    }
}

// Update every minute:
setInterval(updateClocks, 60000);
updateClocks();