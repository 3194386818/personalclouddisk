export function generateBrowserId() {
    let userAgent = navigator.userAgent
    let hash = 0

    if (userAgent.length == 0) {
        return hash
    }

    for (let i = 0; i < userAgent.length; i++) {
        let char = userAgent.charCodeAt(i)
        hash = ((hash << 5) - hash) + char
        hash = hash & hash
    }

    return hash
}
