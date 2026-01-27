import style from '@/app/(beforeLogin)/_style/layout.module.css';

const Layout = ({ children }: { children: React.ReactNode }) => {
    return <div className={style.container}>{children}</div>;
};

export default Layout;
