import React, {useEffect, useState} from "react";
import {useNavigate} from 'react-router-dom';
import api from "../../../services/api";

export default function Promo()
{
    return(
        <div>
            <h1>Welcome, <strong>{localStorage.getItem('username')}!</strong></h1>
        </div>
    );
}