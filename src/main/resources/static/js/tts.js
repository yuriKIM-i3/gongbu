$( document ).ready(function() {
    function speak(text) {
        if (typeof SpeechSynthesisUtterance === "undefined" || typeof window.speechSynthesis === "undefined") {
            alert("ブラウザが音声合成機能を提供しておりません😢")
            return
        }
        
        window.speechSynthesis.cancel()

        const prop = {}
        const speechMsg = new SpeechSynthesisUtterance()
        speechMsg.rate = prop.rate || 1
        speechMsg.pitch = prop.pitch || 1
        speechMsg.lang = prop.lang || "ko-KR"
        speechMsg.text = text

        window.speechSynthesis.speak(speechMsg)
    }

    const text = document.getElementById("text")
    const btnRead = document.getElementById("btn-read")

    btnRead.addEventListener("click", e => {
        speak(text.value)
    })
});