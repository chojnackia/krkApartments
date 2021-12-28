import React from "react";
import "./App.css";
import { BrowserRouter } from "react-router-dom";
import RouterComponent from "./routes/RouterComponent";

function App() {
  return (
    <BrowserRouter>
      <RouterComponent />
    </BrowserRouter>
  );
}

export default App;
