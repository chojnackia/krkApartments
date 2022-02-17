import React from "react";
import "./App.css";
import { BrowserRouter } from "react-router-dom";
import RouterComponent from "./routes/RouterComponent";
// import Navbar from "./components/Navbar";
// import Menu from "./components/Menu";
import "flatpickr/dist/themes/material_green.css";

function App() {
  return (
    <BrowserRouter>
      {/*<Navbar/>*/}
      <RouterComponent />
      {/*<Menu/>*/}
    </BrowserRouter>
  );
}

export default App;
