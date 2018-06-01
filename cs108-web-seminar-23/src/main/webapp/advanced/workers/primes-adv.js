function calculateNextPrime(num) {
    while (true) {
        var isPrime = true;
        for (var i = 2; i < num; ++i) {
            if (num % i === 0) {
                isPrime = false;
                break;
            }
        }
        if (isPrime) {
            return num;
        }
        num = num + 1;
    }
}

function handleMessage(evt) {
    var nextPrime = calculateNextPrime(parseInt(evt.data) + 1);

    this.postMessage(nextPrime);
}

self.onmessage = handleMessage;
