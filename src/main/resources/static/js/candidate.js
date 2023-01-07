async function fetchCandidates() {
    const response = await fetch("http://localhost:8080/candidate/candidatesAPI");
    if (!response.ok) {
        const message = `An error has occurred: ${response.status}`;
        throw new Error(message);
    } else {
        const candidates = await response.json();
        return candidates;
    }
}
document.getElementById("candidates").innerHTML = "Paragraph changed.";

fetchCandidates().then(candidates => {
    console.log(candidates);
});