import React, {useEffect, useState} from "react";
import {useNavigate, Link} from 'react-router-dom';

export default function Pedido()
{
    const [clienteLogado, setClienteLogado] = useState('');
    const [tokenClienteLogado, setTokenClienteLogado] = useState('');

    const [pedidosCliente, setPedidosClientes] = useState([]);
    const [pedidoAtivo, setPedidoAtivo] = useState(false);

    const navigate = useNavigate ();

    useEffect(() => {

    })

    useEffect(() => {
        const getCliente = localStorage.getItem('username');
        const getTokenCliente = localStorage.getItem('clienteToken')
        if (getCliente && getTokenCliente)
        {
            setTokenClienteLogado(getTokenCliente);
            setClienteLogado(getCliente);
        }
        else{
            alert('É ncessário estar logado para acessar esta área, voltando para a página inicial.')
            navigate('/home')
        }
    }, [clienteLogado])

     useEffect(() => {

     })

    return (
        <div>
            {pedidoAtivo !== false ? (
                <div>
                    <h2>
                        Batata doce e frango
                    </h2>
                </div>    
                ) : (
                    <div>
                        <h2>
                            Olá, <strong>{clienteLogado}</strong>, esta é a sua área de pedidos. Assim que um pedido for feito ele aparecerá aqui!
                        </h2>
                    </div>
                )
            }
        </div>
    );
}