import React from "react";
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import Login from "./pages/ClientSide/LoginPage/Login";
import Promo from "./pages/ClientSide/PromoPage/Promo";
import Home from "./pages/ClientSide/HomePage/Home";
import Profile from "./pages/ClientSide/ProfilePage/Profile";
import Carrinho from "./pages/ClientSide/CarrinhoPage/Carrinho";
import Mesa from "./pages/ClientSide/MesaPage/Mesa";
import Error from "./pages/ClientSide/ErrorPage/Error";
import Pedido from "./pages/ClientSide/PedidosPage/Pedido";

export default function Rotas(){
    return(
        <Router>
            <Routes> 
                <Route path="/login" Component={Login}/>
                <Route path="/promo" Component={Promo}/>
                <Route path="/home" Component={Home}/>
                <Route path="/cliente/perfil" Component={Profile}/>
                <Route path="/cliente/carrinho" Component={Carrinho}/>
                <Route path="/mesa/:id" Component={Mesa} />
                <Route path="/error/page" Component={Error}/>
                <Route path="/cliente/pedidos" Component={Pedido}/>
            </Routes>
        </Router>
    );
}