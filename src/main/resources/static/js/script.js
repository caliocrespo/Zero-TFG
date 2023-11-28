const clickAreas = document.querySelectorAll(".stars div");
const stars = document.querySelectorAll(".stars i");
var rating;

clickAreas.forEach((area, i) => {
	area.addEventListener("click", () => {
		stars.forEach((star) => (star.className = "bx bx-star"));
		const starCount = (i + 1) / 2;

		for (let j = 0; j < starCount; j++) {
			if (starCount - j === 0.5 && j === starCount - 0.5) {
				stars[j].className = "bx bxs-star-half";
			} else {
				stars[j].className = "bx bxs-star";
			}
		}
		document.getElementById("ownRating").value = starCount;
		document.querySelector(".rating span").textContent = starCount;


	});
});

function setStars() {
	const stars = document.querySelectorAll(".stars i");
	const rating = parseFloat(document.getElementById("ownRating").value);
	console.log("Own Rating:", rating);
	stars.forEach((star, index) => {
		const starValue = index;

		if (rating - starValue == 0.5) {
			star.className = "bx bxs-star-half";
		} else if (rating > starValue) {
			star.className = "bx bxs-star";
		}
	});

	document.querySelector(".rating span").textContent = rating;
}

setStars();

function rateGame(rating) {
	for (let i = 1; i <= 4; i++) {
		const icon = document.getElementById(`icon${i}`);
		const text = document.getElementById(`text${i}`);
		icon.src = `/img/icon${i}.png`;
		text.style.color = 'grey';
	}

	const icon = document.getElementById(`icon${rating}`);
	const text = document.getElementById(`text${rating}`);
	icon.src = `/img/iconfilled${rating}.png`;
	text.style.color = 'white';

	document.getElementById("ownStatus").value = rating;
}

function setStatus() {
	const status = document.getElementById("ownStatus").value;
	if (status == "Playing") {
		const icon = document.getElementById(`icon${1}`);
		const text = document.getElementById(`text${1}`);
		icon.src = `/img/iconfilled${1}.png`;
		text.style.color = 'white';
	} else if (status == "Completed") {
		const icon = document.getElementById(`icon${2}`);
		const text = document.getElementById(`text${2}`);
		icon.src = `/img/iconfilled${2}.png`;
		text.style.color = 'white';
	} else if (status == "Dropped") {
		const icon = document.getElementById(`icon${3}`);
		const text = document.getElementById(`text${3}`);
		icon.src = `/img/iconfilled${3}.png`;
		text.style.color = 'white';
	} else if (status == "Plan To Watch") {
		const icon = document.getElementById(`icon${4}`);
		const text = document.getElementById(`text${4}`);
		icon.src = `/img/iconfilled${4}.png`;
		text.style.color = 'white';
	}
}

setStatus();



