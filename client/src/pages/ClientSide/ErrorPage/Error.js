import React, {useEffect, useState} from "react";
import { CiShoppingCart as Carrinho} from "react-icons/ci";
import {useNavigate, Link} from 'react-router-dom';
import api from "../../../services/api";
import error from './assets/error.jpg'

export default function Error()
{
    return(
        <div>
            <h1>Oooopss!</h1>
            <h2>Parece que voce teve um erro de URL, por favor, tente outra rota!</h2>
            <img src = {error} alt="error"/>
        </div>
    );
}