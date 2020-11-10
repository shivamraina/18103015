let done = false;

function printRandLeaf() { 
    process.stdout.write("0 ");
} 

function triangle(f, n, toth) { 
    let i, j, k = 2 * toth - 2; 
  
    for (i = 0; i < f - 1; i++) 
        k--;

    for (i = f - 1; i < n; i++) {
        for (j = 0; j < k; j++) 
            process.stdout.write(" ");
        k = k - 1;
        for (j = 0; j <= i; j++) 
        	if(j === 0 && f === 1 && n === 4 && !done) {
        		process.stdout.write("* ");
        		done = true;
        	}
        	else printRandLeaf();
        console.log('\n');
    } 
} 

function printTree(h) { 
    let start = 1, stop = 0, diff = 3; 
    while (stop < h + 1) { 
        stop = start + diff; 
        triangle(start, stop, h); 
        diff++; 
        start = stop - 2; 
    } 
}

function printLog(n) {
    let i, j, k = 2 * n - 4;
    for (i = 1; i <= 6; i++) {
        for (j = 0; j < k; j++) 
            process.stdout.write(" ");
        for (j = 1; j <= 6; j++) 
            process.stdout.write("#");
        console.log('\n');
    }
}

printTree(6);
printLog(6); 