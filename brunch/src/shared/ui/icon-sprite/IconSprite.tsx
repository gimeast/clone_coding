export const IconSprite = ({
    src,
    x,
    y,
    width,
    height,
}: {
    src: string;
    x: number;
    y: number;
    width: number;
    height: number;
}) => {
    return (
        <div
            style={{
                width: `${width}px`,
                height: `${height}px`,
                backgroundImage: `url(${src})`,
                backgroundPosition: `-${x}px -${y}px`,
                backgroundRepeat: 'no-repeat',
            }}
        />
    );
};
