import React from "react";
import "./App.css";
import { BrowserRouter } from "react-router-dom";
import RouterComponent from "./routes/RouterComponent";
import Navbar from "./components/Navbar";
import Menu from "./components/Menu";

function App() {
  return (
    <BrowserRouter>
    <Navbar/>
      <RouterComponent />
    <Menu/>
    </BrowserRouter>
  );
}

export default App;

// import React, { useEffect, useState } from "react";
// import "./App.css";
// import { BrowserRouter } from "react-router-dom";
// import RouterComponent from "./routes/RouterComponent";
// import {API_BASE_URL} from "./config";
// import {Container} from "react-bootstrap";
// import ApartmentList from "./components/ApartmentList";
// import InputComponent from "./components/InputComponent";




// const App: React.FC = () => {
//   const [apartments, setApartments] = useState<IApartment[]>([])

//   const init = () => {
//     fetch('http://localhost:8080/apartments/',{
//       method: 'GET',
//             headers: {
//                 'Accept': 'application/json',
//                 'Content-Type': 'application/json'
//             }
//           }).then(response => {
//             setApartments(response.data);
//           })


//   useEffect(() => {
//     init();
//   }, []);

  

  
//   return (
//     <div className="App">
//       <main>
//         <Container>
//         {/* <h1 className={"display-1 text-center"}>Krk App</h1> */}
//         <table className="table table-striped table-bordered">
//                     <thead>
//                     <tr>
//                         <td>Apartment name</td>
//                         <td>Description</td>
//                         <td>Price</td>
                       
//                     </tr>

//                     </thead>
//                     <tbody>
//                     {
//                         apartments.map(apartment => (
//                                 <tr key={apartment.id}>
//                                     <td>{apartment.name}</td>
//                                     <td>{apartment.description}</td>
//                                     <td>{apartment.price}</td>
//                                     <td>
                                        
//                                     </td>
//                                 </tr>
//                         ))
//                     }
//                     </tbody>
//                 </table>
//         </Container>
//       </main>
//     </div>
//   );
// }

// export default App;
