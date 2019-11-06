import React,{Component} from 'react';
import Table from "./TableAd"
import './Home.css'
import SearchForm from './SearchForm'
import Menu from './Menu'
class Home extends Component{
 render() {
     return(<div>
             <Menu></Menu>
             <div className='Home'>
                 <h1>Livinir</h1>
                 <SearchForm/>
                 <Table/>
             </div>
     </div>
     )
 }
}
export default Home;