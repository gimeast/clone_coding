import style from './searchInput.module.css';
import { Search } from 'lucide-react';

const SearchInput = () => {
    return (
        <form className={style.searchWrapper}>
            <div className={style.searchBorder}>
                <Search width={16} />
                <label className='sr-only' htmlFor='search'>
                    검색
                </label>
                <input type='text' id='search' placeholder='Search' />
            </div>
        </form>
    );
};

export default SearchInput;
