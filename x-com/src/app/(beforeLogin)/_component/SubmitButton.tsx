import style from './submitButton.module.css';

const SubmitButton = ({ text }: { text: string }) => {
    return (
        <button className={style.button} type='submit'>
            {text}
        </button>
    );
};

export default SubmitButton;
