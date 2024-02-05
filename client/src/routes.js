import React from "react";
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import Order from "./pages/ClientSide/Order/Order";

export default function Rotas(){
    return(
        <Router>
            <Routes> 
                <Route path="/pedido" Component={Order}/>
            </Routes>
        </Router>
    );
}