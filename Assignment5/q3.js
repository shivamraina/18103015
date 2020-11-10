function checker(str) {
	let arr = [];
	for(ch of str) {
		if(ch === '[' || ch === '{' || ch === '(') {
			arr.push(ch);
		}
		else if(ch === ']') {
			if(arr.length === 0) {
				return 0;
			}
			let found = false;
			while(arr.length>0) {
				if(arr[arr.length-1] ===  '[') {
					found = true;
					arr.pop();
					break;
				}
				arr.pop();
			}
			if(!found) return 0;
		}
		else if(ch === '}') {
			if(arr.length === 0) {
				return 0;
			}
			let found = false;
			while(arr.length>0) {
				if(arr[arr.length-1] ===  '{') {
					found = true;
					arr.pop();
					break;
				}
				arr.pop();
			}
			if(!found) return 0;
		}
		else if(ch === ')') {
			if(arr.length === 0) {
				return 0;
			}
			let found = false;
			while(arr.length>0) {
				if(arr[arr.length-1] ===  '(') {
					found = true;
					arr.pop();
					break;
				}
				arr.pop();
			}
			if(!found) return 0;
		}
	}
	return 1;
}

const readline = require('readline').createInterface({
	input: process.stdin,
  	output: process.stdout
});

readline.question('Enter a String\n', string => {
  	if(checker(string)) {
  		console.log('Parenthesis Balanced');
  	}
  	else {
  		console.log('Parenthesis Not Balanced');	
  	}
  	readline.close();
});