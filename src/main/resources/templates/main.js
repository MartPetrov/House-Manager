
// const peopleInDB = async () => {
//     let response = await fetch("http://localhost:8080/getAllPeople")
//
//     console.log(response)
//     const peopleData = await response;
//     const peopleList = document.getElementById("preview-list");
//
//     for (const people of Object.values(peopleData)) {
//         const h2Element = document.createElement("h2");
//         h2Element.textContent = people;
//         peopleList.appendChild(h2Element);
//     }
//
// }