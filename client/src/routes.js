import React from "react";
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import Order from "./pages/ClientSide/Order/Order";
import Login from "./pages/ClientSide/Login/Login";

export default function Rotas(){
    return(
        <Router>
            <Routes> 
                <Route path="/pedido" Component={Order}/>
                <Route path="/login" Component={Login}/>
            </Routes>
        </Router>
    );
}