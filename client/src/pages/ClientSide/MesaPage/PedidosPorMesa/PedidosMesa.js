import React, {useEffect, useState} from "react";
import api from "../../../../services/api";
import { useParams, useNavigate , Link} from "react-router-dom";

export default function PedidosMesa()
{
    const [pedidos, setPedidos] = useState([]);
    const [mesaToken, setMesaToken] = useState('');
    const navigate = useNavigate();

    function getErrorMessage(error)
    {
        const message = error.response.data.message;
        if (message)
        {
            alert(message);
        }
        else{
            alert('ERRO DE REQUISICAO OU/DE URL.');
        }
    }
    
    useEffect(() => {
        async function startPage()
        {
            const clienteToken = localStorage.getItem('clienteToken');
            if (clienteToken)
            {
                api.post('http://localhost:8080/api/mesa/cliente/get-by-cliente-id/' + clienteToken)
                .then(responseIdMesa => {
                    const idMesa = responseIdMesa.data.idMesa;
                    if (idMesa)
                    {
                        setMesaToken(idMesa);    
                        api.post('http://localhost:8080/api/pedido/get-by-mesa-id/' + idMesa)
                        .then(async responsePedidos => {
                            setPedidos(responsePedidos.data);
                        })  
                        .catch(erro => {getErrorMessage(erro)})                  
                    }   
                    else{
                        alert('Um erro ocorreu ao tentar buscar a mesa do cliente.');
                    }
                })
                .catch(error => {getErrorMessage(error)})
            }
            else{
                navigate('/login');
            }
        }

        startPage();
    }, [pedidos])

    return (
        <div>
            hello, world
        </div>
    );
}