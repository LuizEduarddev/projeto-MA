import React from "react";
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import Order from "./pages/ClientSide/OrderPage/Order";
import Login from "./pages/ClientSide/LoginPage/Login";
import Promo from "./pages/ClientSide/PromoPage/Promo";

export default function Rotas(){
    return(
        <Router>
            <Routes> 
                <Route path="/pedido" Component={Order}/>
                <Route path="/login" Component={Login}/>
                <Route path="/promo" Component={Promo}/>
            </Routes>
        </Router>
    );
}