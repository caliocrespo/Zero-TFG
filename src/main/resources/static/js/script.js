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
        } else if(rating > starValue) {
            star.className = "bx bxs-star";
        }
    });

    document.querySelector(".rating span").textContent = rating;
}

setStars();




