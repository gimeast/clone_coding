import style from './backButton.module.css';
import { ArrowLeft } from 'lucide-react';

const BackButton = () => {
    return (
        <button className={style.backButton}>
            <ArrowLeft />
        </button>
    );
};

export default BackButton;
