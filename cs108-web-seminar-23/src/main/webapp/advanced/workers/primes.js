function calculatePrimes() {
    var num = 1;
    while (true) {
        var isPrime = true;
        for (var i = 2; i < num; ++i) {
            if (num % i === 0) {
                isPrime = false;
                break;
            }
        }
        if (isPrime) {
            this.postMessage(num);
        }
        num = num + 1;
    }
}

calculatePrimes();
