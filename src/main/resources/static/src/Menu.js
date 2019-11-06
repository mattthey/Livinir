import logotype from "./image/L1.png";
import {Link} from "react-router-dom";
import React,{Component} from 'react';
import './Menu.css'

class Menu extends Component{
    constructor(props){
        super(props)
    }
        render(){return(<div className='Menu'>
            <img src={logotype} alt='logotype' height='90' width='100'/>
            <ul className='nav'>
                <li><Link to='/'>Home</Link></li>
                <li><Link to='/signUp'>Sign up</Link></li>
                <li><Link to='/signIn'>Sign in</Link></li>
                <li><Link to='/profile'>Profile</Link></li>
                <li><Link to='/chat'>Chat</Link></li>
            </ul>
        </div>)
    }
}
export default Menu;
